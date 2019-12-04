package com.example.finalproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

private const val TAG5 = "BoxDrawingView"
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CFViewTesting @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var Player1Paint = Paint()
    var Player2Paint = Paint()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        Player1Paint.color = Color.RED
        Player2Paint.color = Color.YELLOW

        for (point in circles) {
            if (point.player == 1)
                canvas.drawCircle(point.center.x, point.center.y, 90F, Player1Paint)
            else
                canvas.drawCircle(point.center.x, point.center.y, 90F, Player2Paint)

        }

    }
}