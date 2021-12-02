package com.felipepolo.globalhit

import junit.framework.TestCase
import org.junit.Test

class SortColorsTest : TestCase() {

    @Test
    fun testGetArrayOfColors() {
        var sortColors = SortColors()
        assertNotNull(sortColors.getArrayOfColors())
        println(sortColors.getArrayOfColors())
    }
}