
package score.validation;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseOptionalName extends CellProcessorAdaptor {

	private String which;
	
	public ParseOptionalName(String w) {
		super();
		which = w;
	}
	
	public ParseOptionalName(CellProcessor next) {
		super(next);
	}
	
	@Override
	public <T> T execute(Object value, CsvContext context) {		

		if (value == null) {
			return next.execute(null, context);
		}
		String s = value.toString();
		if (s.length() <= 15) {
		 return next.execute(value, context);
		}
		throw new SuperCsvCellProcessorException(
                String.format(which + " name is too long: " + s, value), context, this);
	}

	
	
}
