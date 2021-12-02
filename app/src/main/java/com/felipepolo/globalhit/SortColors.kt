package com.felipepolo.globalhit

class SortColors {
    fun getArrayOfColors(): ArrayList<Colors> {
        DefaultColors.COLORS.shuffle()
        val matrix = ArrayList<Colors>()
        var cont = 0
        for (i in 0..3) {
            matrix.add(
                Colors(
                    DefaultColors.COLORS[cont],
                    DefaultColors.COLORS[cont + 1],
                    DefaultColors.COLORS[cont + 2]
                )
            )
            cont += 3
        }
        return matrix
    }
}