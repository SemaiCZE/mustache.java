package mustachejava.benchmarks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ComplexBench {
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	@Fork(2)
	@Warmup(iterations = 15)
	@Measurement(iterations = 15)
	public void testCompilation(Blackhole bh) throws IOException {
		DefaultMustacheFactory dmf = new DefaultMustacheFactory();
		Mustache m = dmf.compile("complexbench.mustache");

		StringWriter output = new StringWriter();
		InputStream json = ComplexBench.class.getClassLoader().getResourceAsStream("complexbench.json");
		Object scope = new ObjectMapper().readValue(json, HashMap.class);

		m.execute(output, scope).flush();
		// System.out.println(output.toString());
		bh.consume(output.toString());
	}
}
