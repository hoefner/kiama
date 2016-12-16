/*
 * This file is part of Kiama.
 *
 * Copyright (C) 2014-2016 Anthony M Sloane, Macquarie University.
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
package relation

import org.bitbucket.inkytonik.kiama.util.Tests

/**
 * Tests of binary relations.
 */
class RelationTests extends Tests {

    import org.bitbucket.inkytonik.kiama.example.imperative.ImperativeTree.Num
    import org.bitbucket.inkytonik.kiama.relation.Relation.{fromPairs, graphFromPairs}

    // Empty relations

    val emptyIntBool = new Relation[Int, Boolean](graphFromPairs(Vector()))
    val emptyNumInt = new Relation[Num, Int](graphFromPairs(Vector()))
    val emptyBoolNum = new Relation[Boolean, Num](graphFromPairs(Vector()))
    val emptyNumNum = new Relation[Num, Num](graphFromPairs(Vector()))

    // Singleton relations

    val num2 = Num(2)
    val num3 = Num(2) // deliberately == to num2, but not same
    val num4 = Num(4)
    val num5 = Num(5)

    val singleIntBool = fromPairs(Vector((1, true)))
    val singleNumInt = fromPairs(Vector((num2, 2)))
    val singleBoolNum = fromPairs(Vector((false, num3)))
    val singleNumNum = fromPairs(Vector((num4, num5)))

    // Multiple element relations

    val multiIntBool = fromPairs(Vector((1, true), (2, false), (1, true)))
    val multiNumInt = fromPairs(Vector((num2, 2), (num3, 3)))
    val multiBoolNum = fromPairs(Vector((false, num3), (false, num4), (true, num4)))
    val multiNumNum = fromPairs(Vector((num4, num5), (num4, num5)))

    // containsInDomain

    test("domain of singleton value-value relation contains its element") {
        singleIntBool.containsInDomain(1) shouldBe true
    }

    test("domain of singleton value-value relation doesn't contain a non-element") {
        singleIntBool.containsInDomain(2) shouldBe false
    }

    test("domain of singleton ref-value relation contains its element") {
        singleNumInt.containsInDomain(num2) shouldBe true
    }

    test("domain of singleton ref-value relation doesn't contain a non-element") {
        singleNumInt.containsInDomain(num3) shouldBe false
    }

    test("domain of singleton value-ref relation contains its element") {
        singleBoolNum.containsInDomain(false) shouldBe true
    }

    test("domain of singleton value-ref relation doesn't contain a non-element") {
        singleBoolNum.containsInDomain(true) shouldBe false
    }

    test("domain of singleton ref-ref relation contains its element") {
        singleNumNum.containsInDomain(num4) shouldBe true
    }

    test("domain of singleton ref-ref relation doesn't contain a non-element") {
        singleNumNum.containsInDomain(num5) shouldBe false
    }

    test("domain of multiple element value-value relation contains its first element") {
        multiIntBool.containsInDomain(1) shouldBe true
    }

    test("domain of multiple element value-value relation contains its second element") {
        multiIntBool.containsInDomain(2) shouldBe true
    }

    test("domain of multiple element value-value relation doesn't contain a non-element") {
        multiIntBool.containsInDomain(3) shouldBe false
    }

    test("domain of multiple element ref-value relation contains its first element") {
        multiNumInt.containsInDomain(num2) shouldBe true
    }

    test("domain of multiple element ref-value relation contains its second element") {
        multiNumInt.containsInDomain(num3) shouldBe true
    }

    test("domain of multiple element ref-value relation doesn't contain a non-element") {
        multiNumInt.containsInDomain(num4) shouldBe false
    }

    test("domain of multiple element value-ref relation contains its first element") {
        multiBoolNum.containsInDomain(false) shouldBe true
    }

    test("domain of multiple element value-ref relation contains its second element") {
        multiBoolNum.containsInDomain(true) shouldBe true
    }

    test("domain of multiple element ref-ref relation contains its element") {
        multiNumNum.containsInDomain(num4) shouldBe true
    }

    test("domain of multiple element ref-ref relation doesn't contain a non-element") {
        multiNumNum.containsInDomain(num5) shouldBe false
    }

    // containsInRange

    test("range of singleton value-value relation contains its element") {
        singleIntBool.containsInRange(true) shouldBe true
    }

    test("range of singleton value-value relation doesn't contain a non-element") {
        singleIntBool.containsInRange(false) shouldBe false
    }

    test("range of singleton ref-value relation contains its element") {
        singleNumInt.containsInRange(2) shouldBe true
    }

    test("range of singleton ref-value relation doesn't contain a non-element") {
        singleNumInt.containsInRange(3) shouldBe false
    }

    test("range of singleton value-ref relation contains its element") {
        singleBoolNum.containsInRange(num3) shouldBe true
    }

    test("range of singleton value-ref relation doesn't contain a non-element") {
        singleBoolNum.containsInRange(num2) shouldBe false
    }

    test("range of singleton ref-ref relation contains its element") {
        singleNumNum.containsInRange(num5) shouldBe true
    }

    test("range of singleton ref-ref relation doesn't contain a non-element") {
        singleNumNum.containsInRange(num4) shouldBe false
    }

    test("range of multiple element value-value relation contains its first element") {
        multiIntBool.containsInRange(true) shouldBe true
    }

    test("range of multiple element value-value relation contains its second element") {
        multiIntBool.containsInRange(false) shouldBe true
    }

    test("range of multiple element ref-value relation contains its first element") {
        multiNumInt.containsInRange(2) shouldBe true
    }

    test("range of multiple element ref-value relation contains its second element") {
        multiNumInt.containsInRange(3) shouldBe true
    }

    test("range of multiple element ref-value relation doesn't contain a non-element") {
        multiNumInt.containsInRange(4) shouldBe false
    }

    test("range of multiple element value-ref relation contains its first element") {
        multiBoolNum.containsInRange(num3) shouldBe true
    }

    test("range of multiple element value-ref relation contains its second element") {
        multiBoolNum.containsInRange(num4) shouldBe true
    }

    test("range of multiple element value-ref relation doesn't contain a non-element") {
        multiBoolNum.containsInRange(num2) shouldBe false
    }

    test("range of multiple element ref-ref relation contains its element") {
        multiNumNum.containsInRange(num5) shouldBe true
    }

    test("range of multiple element ref-ref relation doesn't contain a non-element") {
        multiNumNum.containsInRange(num4) shouldBe false
    }

    // domain

    test("domain of empty value-value relation is empty") {
        emptyIntBool.domain shouldBe empty
    }

    test("domain of empty ref-value relation is empty") {
        emptyNumInt.domain shouldBe empty
    }

    test("domain of empty value-ref relation is empty") {
        emptyBoolNum.domain shouldBe empty
    }

    test("domain of empty ref-ref relation is empty") {
        emptyNumNum.domain shouldBe empty
    }

    test("domain of singleton value-value relation is correct") {
        singleIntBool.domain should haveSameElementsAs(Vector(1))
    }

    test("domain of singleton ref-value relation is correct") {
        singleNumInt.domain should haveSameElementsAs(Vector(num2))
    }

    test("domain of singleton value-ref relation is correct") {
        singleBoolNum.domain should haveSameElementsAs(Vector(false))
    }

    test("domain of singleton ref-ref relation is correct") {
        singleNumNum.domain should haveSameElementsAs(Vector(num4))
    }

    test("domain of multiple element value-value relation is correct") {
        multiIntBool.domain should haveSameElementsAs(Vector(1, 2))
    }

    test("domain of multiple element ref-value relation is correct") {
        multiNumInt.domain should haveSameElementsAs(Vector(num2, num3))
    }

    test("domain of multiple element value-ref relation is correct") {
        multiBoolNum.domain should haveSameElementsAs(Vector(false, true))
    }

    test("domain of multiple element ref-ref relation is correct") {
        multiNumNum.domain should haveSameElementsAs(Vector(num4))
    }

    // image

    test("image of empty value-value relation is empty") {
        emptyIntBool.image(1) shouldBe empty
    }

    test("image of empty ref-value relation is empty") {
        emptyNumInt.image(num2) shouldBe empty
    }

    test("image of empty value-ref relation is empty") {
        emptyBoolNum.image(false) shouldBe empty
    }

    test("image of empty ref-ref relation is empty") {
        emptyNumNum.image(num3) shouldBe empty
    }

    test("image of singleton value-value relation is correct (present)") {
        singleIntBool.image(1) should haveSameElementsAs(Vector(true))
    }

    test("image of singleton value-value relation is empty (not present)") {
        singleIntBool.image(2) shouldBe empty
    }

    test("image of singleton ref-value relation is correct (present)") {
        singleNumInt.image(num2) should haveSameElementsAs(Vector(2))
    }

    test("image of singleton ref-value relation is empty (not present)") {
        singleNumInt.image(num3) shouldBe empty
    }

    test("image of singleton value-ref relation is correct (present)") {
        singleBoolNum.image(false) should haveSameElementsAs(Vector(num3))
    }

    test("image of singleton value-ref relation is empty (not present)") {
        singleBoolNum.image(true) shouldBe empty
    }

    test("image of singleton ref-ref relation is correct (present)") {
        singleNumNum.image(num4) should haveSameElementsAs(Vector(num5))
    }

    test("image of singleton ref-ref relation is empty (not present)") {
        singleNumNum.image(num5) shouldBe empty
    }

    test("image of multiple element value-value relation is correct (present 1)") {
        multiIntBool.image(1) should haveSameElementsAs(Vector(true, true))
    }

    test("image of multiple element value-value relation is correct (present 2)") {
        multiIntBool.image(2) should haveSameElementsAs(Vector(false))
    }

    test("image of multiple element value-value relation is empty (not present)") {
        multiIntBool.image(3) shouldBe empty
    }

    test("image of multiple element ref-value relation is correct (present)") {
        multiNumInt.image(num2) should haveSameElementsAs(Vector(2))
    }

    test("image of multiple element ref-value relation is empty (not present)") {
        multiNumInt.image(num4) shouldBe empty
    }

    test("image of multiple element value-ref relation is correct (present 1)") {
        multiBoolNum.image(false) should haveSameElementsAs(Vector(num3, num4))
    }

    test("image of multiple element value-ref relation is correct (present 2)") {
        multiBoolNum.image(true) should haveSameElementsAs(Vector(num4))
    }

    test("image of multiple element ref-ref relation is correct (present)") {
        multiNumNum.image(num4) should haveSameElementsAs(Vector(num5, num5))
    }

    test("image of multiple element ref-ref relation is empty (not present)") {
        multiNumNum.image(num2) shouldBe empty
    }

    // inverse

    test("inverting an empty relation yields an empty relation") {
        emptyIntBool.inverse shouldBe empty
    }

    test("inverting a singleton relation yields the correct singleton relation") {
        val graph = singleIntBool.inverse.graph
        graph.size shouldBe 1
        graph.image(true) shouldBe Vector(1)
    }

    test("inverting a multiple relation yields the correct multiple relation") {
        val graph = multiBoolNum.inverse.graph
        graph.size shouldBe 2
        graph.image(num3) shouldBe Vector(false)
        val num4image = graph.image(num4)
        num4image.size shouldBe 2
        num4image should contain(false)
        num4image should contain(true)
    }

    // range

    test("range of empty value-value relation is empty") {
        emptyIntBool.range shouldBe empty
    }

    test("range of empty ref-value relation is empty") {
        emptyNumInt.range shouldBe empty
    }

    test("range of empty value-ref relation is empty") {
        emptyBoolNum.range shouldBe empty
    }

    test("range of empty ref-ref relation is empty") {
        emptyNumNum.range shouldBe empty
    }

    test("range of singleton value-value relation is correct") {
        singleIntBool.range should haveSameElementsAs(Vector(true))
    }

    test("range of singleton ref-value relation is correct") {
        singleNumInt.range should haveSameElementsAs(Vector(2))
    }

    test("range of singleton value-ref relation is correct") {
        singleBoolNum.range should haveSameElementsAs(Vector(num3))
    }

    test("range of singleton ref-ref relation is correct") {
        singleNumNum.range should haveSameElementsAs(Vector(num5))
    }

    test("range of multiple element value-value relation is correct") {
        multiIntBool.range should haveSameElementsAs(Vector(true, false))
    }

    test("range of multiple element ref-value relation is correct") {
        multiNumInt.range should haveSameElementsAs(Vector(2, 3))
    }

    test("range of multiple element value-ref relation is correct") {
        multiBoolNum.range should haveSameElementsAs(Vector(num3, num4))
    }

    test("range of multiple element ref-ref relation is correct") {
        multiNumNum.range should haveSameElementsAs(Vector(num5))
    }

    // unapplySeq (direct)

    test("unapplySeq of empty value-value relation fails") {
        emptyIntBool.unapplySeq(1) shouldBe Some(Vector())
    }

    test("unapplySeq of empty ref-value relation fails") {
        emptyNumInt.unapplySeq(num2) shouldBe Some(Vector())
    }

    test("unapplySeq of empty value-ref relation fails") {
        emptyBoolNum.unapplySeq(false) shouldBe Some(Vector())
    }

    test("unapplySeq of empty ref-ref relation fails") {
        emptyNumNum.unapplySeq(num3) shouldBe Some(Vector())
    }

    test("unapplySeq of singleton value-value relation is correct (present)") {
        singleIntBool.unapplySeq(1) shouldBe Some(Vector(true))
    }

    test("unapplySeq pair of singleton value-value relation is correct (present)") {
        singleIntBool.pair.unapplySeq(1) shouldBe Some((1, Vector(true)))
    }

    test("unapplySeq of singleton value-value relation fails (not present)") {
        singleIntBool.unapplySeq(2) shouldBe Some(Vector())
    }

    test("unapplySeq of singleton ref-value relation is correct (present)") {
        singleNumInt.unapplySeq(num2) shouldBe Some(Vector(2))
    }

    // This test makes sure that we are comparing nodes by identity since
    // num2 and num3 are equal by value.

    test("unapplySeq pair of singleton ref-value relation doesn't produce equal but not eq value (present)") {
        singleNumInt.pair.unapplySeq(num2) should not(beSameCollectionAs(Some((num3, Vector(2)))))
    }

    test("unapplySeq of singleton ref-value relation fails (not present)") {
        singleNumInt.unapplySeq(num3) shouldBe Some(Vector())
    }

    test("unapplySeq of singleton value-ref relation is correct (present)") {
        singleBoolNum.unapplySeq(false) should beSameCollectionAs(Some(Vector(num3)))
    }

    test("unapplySeq pair of singleton value-ref relation is correct (present)") {
        singleBoolNum.pair.unapplySeq(false) should beSameCollectionAs(Some((false, Vector(num3))))
    }

    test("unapplySeq of singleton value-ref relation fails (not present)") {
        singleBoolNum.unapplySeq(true) shouldBe Some(Vector())
    }

    test("unapplySeq of singleton ref-ref relation is correct (present)") {
        singleNumNum.unapplySeq(num4) should beSameCollectionAs(Some(Vector(num5)))
    }

    test("unapplySeq pair of singleton ref-ref relation is correct (present)") {
        singleNumNum.pair.unapplySeq(num4) should beSameCollectionAs(Some((num4, Vector(num5))))
    }

    test("unapplySeq of singleton ref-ref relation fails (not present)") {
        singleNumNum.unapplySeq(num5) shouldBe Some(Vector())
    }

    test("unapplySeq of multiple element value-value relation fails (multiple)") {
        multiIntBool.unapplySeq(1) shouldBe Some(Vector(true, true))
    }

    test("unapplySeq of multiple element value-value relation is correct (present)") {
        multiIntBool.unapplySeq(2) shouldBe Some(Vector(false))
    }

    test("unapplySeq pair of multiple element value-value relation is correct (present)") {
        multiIntBool.pair.unapplySeq(2) shouldBe Some((2, Vector(false)))
    }

    test("unapplySeq of multiple element value-value relation fails (not present)") {
        multiIntBool.unapplySeq(3) shouldBe Some(Vector())
    }

    test("unapplySeq of multiple element ref-value relation is correct (present)") {
        multiNumInt.unapplySeq(num2) shouldBe Some(Vector(2))
    }

    test("unapplySeq pair of multiple element ref-value relation is correct (present)") {
        multiNumInt.pair.unapplySeq(num2) should beSameCollectionAs(Some((num2, Vector(2))))
    }

    test("unapplySeq of multiple element ref-value relation is correct (not present)") {
        multiNumInt.unapplySeq(num4) shouldBe Some(Vector())
    }

    test("unapplySeq of multiple element value-ref relation fails (multiple)") {
        multiBoolNum.unapplySeq(false) should beSameCollectionAs(Some(Vector(num3, num4)))
    }

    test("unapplySeq of multiple element value-ref relation is correct (present)") {
        multiBoolNum.unapplySeq(true) should beSameCollectionAs(Some(Vector(num4)))
    }

    test("unapplySeq pair of multiple element value-ref relation is correct (present)") {
        multiBoolNum.pair.unapplySeq(true) should beSameCollectionAs(Some((true, Vector(num4))))
    }

    test("unapplySeq of multiple element ref-ref relation is correct (multiple)") {
        multiNumNum.unapplySeq(num4) should beSameCollectionAs(Some(Vector(num5, num5)))
    }

    test("unapplySeq of multiple element ref-ref relation is correct (not present)") {
        multiNumNum.unapplySeq(num2) shouldBe Some(Vector())
    }

    // unapplySeq (via match)

    test("match of an empty relation gives nothing") {
        (1 match {
            case emptyIntBool() => true
            case _              => false
        }) shouldBe true
    }

    test("match of a singleton relation suceeds with exactly one value (present)") {
        (num2 match {
            case singleNumInt(v) => v
            case _               => 0
        }) shouldBe 2
    }

    test("pair match of a singleton relation suceeds with exactly one value (present)") {
        (num2 match {
            case singleNumInt.pair(n, v) => (n, v)
            case _                       => (Num(0), 0)
        }) should beSameCollectionAs((num2, 2))
    }

    test("match of a singleton relation gives nothing (not present)") {
        (num3 match {
            case singleNumInt() => true
            case _              => false
        }) shouldBe true
    }

    test("match of a multiple relation gives multiple values (present)") {
        (1 match {
            case multiIntBool(v1, v2) => (v1, v2)
            case _                    => (false, false)
        }) shouldBe ((true, true))
    }

    test("pair match of a multiple relation gives multiple values (present)") {
        (1 match {
            case multiIntBool.pair(i, v1, v2) => (i, v1, v2)
            case _                            => (0, false, false)
        }) shouldBe ((1, true, true))
    }

    test("match of a multiple relation fails (not present)") {
        (3 match {
            case multiIntBool() => true
            case _              => false
        }) shouldBe true
    }

}