package edu.lecture.android.practice.threading;

import java.io.IOException;

public abstract class Work<T> {
	public abstract T work() throws IOException;
}
