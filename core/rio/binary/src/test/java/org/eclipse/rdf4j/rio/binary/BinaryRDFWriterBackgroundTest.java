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
package org.eclipse.rdf4j.rio.binary;

import java.io.InputStream;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFWriterTest;
import org.eclipse.rdf4j.rio.RioSetting;

/**
 * @author Arjohn Kampman
 */
public class BinaryRDFWriterBackgroundTest extends RDFWriterTest {

	public BinaryRDFWriterBackgroundTest() {
		super(new BinaryRDFWriterFactory(), new BinaryRDFParserFactory());
	}

	@Override
	protected Model parse(InputStream reader, String baseURI)
			throws RDFParseException, RDFHandlerException {
		return QueryResults
				.asModel(QueryResults.parseGraphBackground(reader, baseURI, rdfParserFactory.getRDFFormat(),
						null));
	}

	@Override
	protected RioSetting<?>[] getExpectedSupportedSettings() {
		return new RioSetting[] {
				BinaryRDFWriterSettings.VERSION,
				BinaryRDFWriterSettings.BUFFER_SIZE,
				BinaryRDFWriterSettings.CHARSET,
				BinaryRDFWriterSettings.RECYCLE_IDS
		};
	}

}
