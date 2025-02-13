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

package org.eclipse.rdf4j.common.lang;

import java.util.Objects;

/**
 * Generic utility methods related to objects.
 *
 * @author Arjohn Kampman
 */
public class ObjectUtil {

	/**
	 * Returns the hash code of the supplied object, or <var>0</var> if a null reference is supplied.
	 *
	 * @param o An object or null reference.
	 * @return The object's hash code, or <var>0</var> if the parameter is <var>null</var>.
	 */
	public static int nullHashCode(Object o) {
		return o == null ? 0 : o.hashCode();
	}
}
