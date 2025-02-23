/*******************************************************************************
 * Copyright (c) 2015 Eclipse RDF4J contributors, Aduna, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *******************************************************************************/

package org.eclipse.rdf4j.common.iteration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class consists exclusively of static methods that operate on or return Iterations. It is the
 * Iteration-equivalent of <var>java.util.Collections</var>.
 */
@Deprecated(since = "4.1.0")
public class Iterations {

	/**
	 * Get a List containing all elements obtained from the specified iteration.
	 *
	 * @param iter the {@link CloseableIteration} to get the elements from
	 * @return a List containing all elements obtained from the specified iteration.
	 */
	public static <E, X extends Exception> List<E> asList(CloseableIteration<? extends E> iter) {
		try (iter) {
			// stream.collect is slightly slower than addAll for lists
			List<E> list = new ArrayList<>();

			// addAll closes the iteration
			return addAll(iter, list);
		}
	}

	/**
	 * Get a Set containing all elements obtained from the specified iteration.
	 *
	 * @param iter the {@link CloseableIteration} to get the elements from
	 * @return a Set containing all elements obtained from the specified iteration.
	 */
	public static <E, X extends Exception> Set<E> asSet(CloseableIteration<? extends E> iter) {
		try (Stream<? extends E> stream = iter.stream()) {
			return stream.collect(Collectors.toSet());
		}
	}

	/**
	 * Adds all elements from the supplied {@link CloseableIteration} to the specified collection then closes the
	 * {@link CloseableIteration}.
	 *
	 * @param iter       A {@link CloseableIteration} containing elements to add to the container.
	 * @param collection The collection to add the elements to.
	 * @return The <var>collection</var> object that was supplied to this method.
	 */
	public static <E, X extends Exception, C extends Collection<E>> C addAll(CloseableIteration<? extends E> iter,
			C collection) {
		try (iter) {
			while (iter.hasNext()) {
				collection.add(iter.next());
			}
		}

		return collection;
	}

	/**
	 * Get a sequential {@link Stream} with the supplied {@link CloseableIteration} as its source. The source iteration
	 * will be automatically closed by the stream when done. Any checked exceptions thrown at any point during stream
	 * processing will be propagated wrapped in a {@link RuntimeException}.
	 *
	 * @param iteration a source {@link CloseableIteration} for the stream.
	 * @return a sequential {@link Stream} object which can be used to process the data from the source iteration.
	 */
	public static <T> Stream<T> stream(CloseableIteration<T> iteration) {
		return StreamSupport
				.stream(new CloseableIterationSpliterator<>(iteration), false)
				.onClose(() -> {
					try {
						iteration.close();
					} catch (RuntimeException e) {
						throw e;
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							Thread.currentThread().interrupt();
						}
						throw new RuntimeException(e);
					}
				});
	}

	/**
	 * Converts a {@link CloseableIteration} to a string by concatenating all the string representations of objects in
	 * the iteration, divided by a separator.
	 *
	 * @param iteration A {@link CloseableIteration} over arbitrary objects that are expected to implement
	 *                  {@link Object#toString()}.
	 * @param separator The separator to insert between the object strings.
	 * @return A String representation of the objects provided by the supplied iteration.
	 */
	public static <X extends Exception> String toString(CloseableIteration<?> iteration, String separator) {
		try (iteration) {
			StringBuilder sb = new StringBuilder();
			toString(iteration, separator, sb);
			return sb.toString();
		}
	}

	/**
	 * Converts a {@link CloseableIteration} to a string by concatenating all the string representations of objects in
	 * the iteration, divided by a separator.
	 *
	 * @param iteration A {@link CloseableIteration} over arbitrary objects that are expected to implement
	 *                  {@link Object#toString()}.
	 * @param separator The separator to insert between the object strings.
	 * @param sb        A StringBuilder to append the iteration string to.
	 */
	public static <X extends Exception> void toString(CloseableIteration<?> iteration, String separator,
			StringBuilder sb)
			throws X {
		try (iteration) {
			while (iteration.hasNext()) {
				sb.append(iteration.next());

				if (iteration.hasNext()) {
					sb.append(separator);
				}
			}
		}

	}

	/**
	 * Get a Set containing all elements obtained from the specified iteration.
	 *
	 * @param iteration the iteration to get the elements from
	 * @param setMaker  the Supplier that constructs a new set
	 * @return a Set containing all elements obtained from the specified iteration.
	 */
	public static <E, X extends Exception> Set<E> asSet(CloseableIteration<? extends E> iteration,
			Supplier<Set<E>> setMaker) {
		Set<E> set = setMaker.get();
		while (iteration.hasNext()) {
			set.add(iteration.next());
		}
		return set;
	}

}
