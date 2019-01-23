
package score;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import score.data.DataLine;
import score.data.Person;
import score.validation.CSVParsingError;
import score.validation.ParseName;
import score.validation.ParseOptionalName;

public class CSVFileReader {

	final String[] outputList = { CSVConstants.ID, CSVConstants.FIRST, CSVConstants.MIDDLE, CSVConstants.LAST,
			CSVConstants.PHONE };

	public Person[] parseFile(String fileName) {
		List<Person> parsedValues = new ArrayList<Person>();
		List<CSVParsingError> parsingProblems = new ArrayList<CSVParsingError>();
		try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName + CSVConstants.CSV),
				CsvPreference.STANDARD_PREFERENCE)) {
			beanReader.getHeader(true);

			final CellProcessor[] processors = getProcessors();
			while (readOneLine(beanReader, outputList, processors, parsedValues, parsingProblems))
				;
			beanReader.close();
		} catch (IOException e) {
			// already caught below
		}

		if (parsingProblems.size() > 0) {
			CSVErrorWriter ew = new CSVErrorWriter();
			ew.writeErrorLog(fileName, parsingProblems);
		}
		return (Person[]) parsedValues.toArray(new Person[parsedValues.size()]);
	}

	private boolean readOneLine(ICsvBeanReader beanReader, String[] outputList, CellProcessor[] processors,
			List<Person> parsedValues, List<CSVParsingError> parsingProblems) throws IOException {
		try {
			DataLine line = beanReader.read(DataLine.class, outputList, processors);
			if (line == null) {
				return false;
			}
			parsedValues.add(line.convertToPerson());

		} catch (Exception e) {
			CSVParsingError anError = new CSVParsingError(beanReader.getLineNumber(), null);
			if (e instanceof SuperCsvException) {
				final int col = ((SuperCsvException) e).getCsvContext().getColumnNumber();
				final String offendingColumn = outputList[col - 1] + ": ";
				anError.setMessage(offendingColumn + e.getMessage());
			} else {
				anError.setMessage(e.getMessage().replace(',',' '));
			}
			parsingProblems.add(anError);
		}
		return true;
	}

	private static CellProcessor[] getProcessors() {
		final String idRegex = "^(?!00000000)\\d{8}";
		final String phoneRegex = "^\\d{3}-\\d{3}-\\d{4}";
		StrRegEx.registerMessage(idRegex, CSVConstants.ID_ERROR);
		StrRegEx.registerMessage(phoneRegex, CSVConstants.PHONE_ERROR);

		return new CellProcessor[] { new StrRegEx(idRegex), new ParseName(CSVConstants.FIRST),
				new ParseOptionalName(CSVConstants.MIDDLE), new ParseName(CSVConstants.LAST),
				new StrRegEx(phoneRegex) };

	}

}
