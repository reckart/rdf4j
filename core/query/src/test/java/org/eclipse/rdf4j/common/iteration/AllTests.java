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

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author akam
 */
@Suite
@SelectClasses({ LimitIterationTest.class, EmptyIterationTest.class, OffsetIterationTest.class,
		ConvertingIterationTest.class, CloseableIteratorIterationTest.class, DelayedIterationTest.class,
		DistinctIterationTest.class, FilterIterationTest.class,
		IntersectionIterationTest.class, DistinctIntersectionIterationTest.class, IteratorIterationTest.class,
		LookAheadIterationTest.class, MinusIterationTest.class, DistinctMinusIterationTest.class,
		SingletonIterationTest.class, UnionIterationTest.class, })
public class AllTests {
}
