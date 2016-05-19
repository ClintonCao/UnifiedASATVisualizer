package BlueTurtle.TSE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a basic unit of work for the analyser to process.
 * 
 * @author BlueTurtle.
 *
 */
@AllArgsConstructor
public class AnalyserCommand {
	@Getter @Setter private String defaultOutputFilePath;
	@Getter @Setter private String[] args;
	@Getter @Setter private boolean redirectOutput;
}
