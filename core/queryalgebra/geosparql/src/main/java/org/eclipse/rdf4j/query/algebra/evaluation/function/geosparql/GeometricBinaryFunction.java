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
package org.eclipse.rdf4j.query.algebra.evaluation.function.geosparql;

import java.io.IOException;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.GEO;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.function.Function;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.Shape;

abstract class GeometricBinaryFunction implements Function {

	@Override
	public Value evaluate(ValueFactory valueFactory, Value... args) throws ValueExprEvaluationException {
		if (args.length != 2) {
			throw new ValueExprEvaluationException(getURI() + " requires exactly 2 arguments, got " + args.length);
		}

		SpatialContext geoContext = SpatialSupport.getSpatialContext();
		Shape geom1 = FunctionArguments.getShape(this, args[0], geoContext);
		Shape geom2 = FunctionArguments.getShape(this, args[1], geoContext);

		String wkt;
		try {
			Shape result = operation(geom1, geom2);
			wkt = SpatialSupport.getWktWriter().toWkt(result);
		} catch (IOException | RuntimeException e) {
			throw new ValueExprEvaluationException(e);
		}
		return valueFactory.createLiteral(wkt, GEO.WKT_LITERAL);
	}

	protected abstract Shape operation(Shape g1, Shape g2);
}
