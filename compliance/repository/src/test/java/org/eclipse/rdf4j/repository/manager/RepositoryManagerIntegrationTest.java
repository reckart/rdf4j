/*******************************************************************************
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *******************************************************************************/
package org.eclipse.rdf4j.repository.manager;

import java.net.URL;
import java.util.Collection;

import org.apache.http.client.HttpClient;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
import org.eclipse.rdf4j.repository.config.RepositoryConfigException;
import org.junit.jupiter.api.BeforeEach;

public class RepositoryManagerIntegrationTest {

	protected RepositoryManager subject;

	@BeforeEach
	public void setUp() {
		subject = new RepositoryManager() {

			@Override
			public void setHttpClient(HttpClient httpClient) {
				// TODO Auto-generated method stub

			}

			@Override
			public URL getLocation() {
				return null;
			}

			@Override
			public HttpClient getHttpClient() {
				return null;
			}

			@Override
			public Collection<RepositoryInfo> getAllRepositoryInfos() throws RepositoryException {
				return null;
			}

			@Override
			protected Repository createRepository(String id) throws RepositoryConfigException, RepositoryException {
				return null;
			}

			@Override
			public RepositoryConfig getRepositoryConfig(String repositoryID)
					throws RepositoryConfigException, RepositoryException {
				return null;
			}

			@Override
			public void addRepositoryConfig(RepositoryConfig config)
					throws RepositoryException, RepositoryConfigException {

			}
		};
	}

}
