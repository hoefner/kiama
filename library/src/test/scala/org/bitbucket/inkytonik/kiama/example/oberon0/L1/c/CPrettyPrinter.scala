/*
 * This file is part of Kiama.
 *
 * Copyright (C) 2011-2016 Anthony M Sloane, Macquarie University.
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with Kiama.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.bitbucket.inkytonik.kiama
package example.oberon0
package L1.c

trait CPrettyPrinter extends L0.c.CPrettyPrinter {

    import base.c.CNode

    override def toDoc(n : CNode) : Doc =
        n match {
            case CIfStatement(c, ts) =>
                "if" <+> parens(toParenDoc(c)) <+> toDoc(ts)

            case CIfElseStatement(c, ts, es) =>
                "if" <+> parens(toParenDoc(c)) <+> toDoc(ts) <+> "else" <+> toDoc(es)

            case CWhileStatement(c, ss) =>
                "while" <+> parens(toParenDoc(c)) <+> toDoc(ss)

            case _ =>
                super.toDoc(n)
        }

}