/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 */
package org.topbraid.shacl.targets;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.rdf.model.Resource;
import org.topbraid.shacl.validation.sparql.SPARQLTargetLanguage;

/**
 * A singleton managing the available custom target plugins.
 * The only currently supported custom target is for SPARQL.
 * 
 * @author Holger Knublauch
 */
public class CustomTargets {

	private static CustomTargets singleton = new CustomTargets();
	
	public static CustomTargets get() {
		return singleton;
	}
	
	
	private final List<CustomTargetLanguage> languages = new LinkedList<>();
	
	CustomTargets() {
		init();
	}
	
	
	public void addLanguage(CustomTargetLanguage plugin) {
		languages.add(plugin);
	}
	
	
	public CustomTargetLanguage getLanguageForTarget(Resource executable) {
		for(CustomTargetLanguage language : languages) {
			if(language.canHandle(executable)) {
				return language;
			}
		}
		return null;
	}
	
	
	private void init() {
		addLanguage(new SPARQLTargetLanguage());
	}
}
