
package score.validation;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseName extends CellProcessorAdaptor {
	
	private String which;

	public ParseName(String w) {
		super();
		which = w;
	}
	
	public ParseName(CellProcessor next) {
		super(next);
	}
	
	@Override
	public <T> T execute(Object value, CsvContext context) {
		if (value == null) {
			throw new SuperCsvCellProcessorException(
	                String.format(which + " name may not be null", value), context, this);
		}
		String s = value.toString();
		if (s.length() <= 15) {
		 return next.execute(value, context);
		}
		throw new SuperCsvCellProcessorException(
                String.format(which + " name is too long: " + s, value), context, this);
	}

	
	
}
