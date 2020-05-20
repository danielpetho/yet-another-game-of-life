package kproc.life

import processing.core.PApplet

fun PApplet.rect(a: Number, b: Number, c: Number, d: Number) {
    this.rect(a.toFloat(), b.toFloat(), c.toFloat(), d.toFloat())
}

fun PApplet.ellipse(a: Number, b: Number, c: Number, d: Number) {
    this.ellipse(a.toFloat(), b.toFloat(), c.toFloat(), d.toFloat())
}
