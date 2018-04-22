package mustachejava.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.io.Writer;

public class BlackholeWriter extends Writer {
	private Blackhole bh;
	public BlackholeWriter(Blackhole bh) {
		this.bh = bh;
	}

	@Override
	public void write(int c) throws IOException {
		bh.consume(c);
	}

	@Override
	public void write(char[] cbuf) throws IOException {
		bh.consume(cbuf);
	}

	@Override
	public void write(String str) throws IOException {
		bh.consume(str);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		bh.consume(str);
		bh.consume(off);
		bh.consume(len);
	}

	@Override
	public Writer append(CharSequence csq) throws IOException {
		bh.consume(csq);
		return this;
	}

	@Override
	public Writer append(CharSequence csq, int start, int end) throws IOException {
		bh.consume(csq);
		bh.consume(start);
		bh.consume(end);
		return this;
	}

	@Override
	public Writer append(char c) throws IOException {
		bh.consume(c);
		return this;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		bh.consume(cbuf);
		bh.consume(off);
		bh.consume(len);
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void close() throws IOException {
	}
}
