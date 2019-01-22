
package score;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import score.validation.CSVParsingError;

public class CSVErrorWriter {

	public void writeErrorLog(String fileName, List<CSVParsingError> parsingProblems) {

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(".\\error-directory\\"+fileName+CSVConstants.CSV), "utf-8"))) {
			writer.write(
					CSVConstants.LINE_NUM + CSVConstants.DELIMITER + CSVConstants.ERROR_MSG + CSVConstants.END_LINE);
			for (CSVParsingError oneError : parsingProblems) {
				writer.write(oneError.toString() + CSVConstants.END_LINE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
