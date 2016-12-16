/*
 * This file is part of Kiama.
 *
 * Copyright (C) 2013-2016 Anthony M Sloane, Macquarie University.
 *
 * Kiama is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Kiama is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesseriniini   General Public License
 * along with Kiama.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.bitbucket.inkytonik.kiama
package example.minijava

import MiniJavaTree.Program
import org.bitbucket.inkytonik.kiama.util.TestCompiler

/**
 * Tests that check that the code generator produces the expected byte code.
 */
class CodeGeneratorTests extends Driver with TestCompiler[Program] {

    val path = "src/test/scala/org/bitbucket/inkytonik/kiama/example/minijava/tests"
    filetests("minijava code generation", path, ".mj", ".out")

}