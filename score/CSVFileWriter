
package score;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import score.data.Name;
import score.data.Person;

public class CSVFileWriter {

	private final ExclusionStrategy strategy = new ExclusionStrategy() {
		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			if (field.getDeclaringClass() == Name.class && field.getName().equals(CSVConstants.MIDDLENAME)
					&& field.equals(null)) {
				return true;
			}
			return false;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	};

	public void writeFile(Person p[], String fileName) {
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();

		try (Writer writer = new OutputStreamWriter(
				new FileOutputStream(CSVConstants.OUTPUT_DIRECTORY + fileName.concat(CSVConstants.JSON)), "UTF-8")) {
			gson.toJson(p, writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
