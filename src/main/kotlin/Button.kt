package kproc.life
import processing.core.PApplet

class Button(p: PApplet, text: String, x: Number, y: Number, w: Number, h: Number) : PApplet() {
    var x: Float = x.toFloat()
    var y: Float = y.toFloat()
    var w: Float = w.toFloat()
    var h: Float = h.toFloat()
    var text: String = text
    var strLen: Int = text.length
    private val parent: PApplet = p

    override fun draw() {
        parent.fill(230)
        parent.rect(x, y, w, h)
        val txtX = x + w / 2f - strLen * 3.5f
        val txtY = y + h / 2f + 5
        parent.fill(10)
        parent.text(text, txtX, txtY)
    }

    fun overButton(): Boolean  {
        return if( parent.mouseX >= x && parent.mouseX <= x + w &&
            parent.mouseY >= y && parent.mouseY <= y + h) {
            parent.fill(250)
            parent.rect(x, y, w, h)
            val txtX = x + w / 2f - strLen * 3.5f
            val txtY = y + h / 2f + 5
            parent.fill(10)
            parent.text(text, txtX, txtY)

            true
        } else {
            false
        }
    }

}