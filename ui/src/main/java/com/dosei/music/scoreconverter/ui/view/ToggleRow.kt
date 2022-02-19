package com.dosei.music.scoreconverter.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dosei.music.scoreconverter.ui.R
import com.dosei.music.scoreconverter.ui.theme.AppTheme

data class Icon(
    val painter: Painter,
    val description: String
)

@Composable
fun ToggleIconRow(
    modifier: Modifier = Modifier,
    icons: List<Icon>,
    selectedIndex: Int,
    onSelectIndex: (Int) -> Unit,
    iconSize: Dp = 48.dp
) {
    val cornerRadius = 8.dp
    Row(
        modifier = modifier
    ) {
        icons.forEachIndexed { index, item ->
            val position = when (index) {
                0 -> ButtonPosition.Start
                icons.lastIndex -> ButtonPosition.End
                else -> ButtonPosition.Middle
            }
            if (index == selectedIndex) {
                SelectedButton(
                    cornerRadius = cornerRadius,
                    index = index,
                    icon = item,
                    iconSize = iconSize,
                    position = position,
                    onClick = { onSelectIndex(index) }
                )
            } else {
                CommonButton(
                    cornerRadius = cornerRadius,
                    index = index,
                    icon = item,
                    iconSize = iconSize,
                    position = position,
                    onClick = { onSelectIndex(index) }
                )
            }
        }
    }
}

private enum class ButtonPosition {
    Start {
        override fun createOutline(radius: Dp): RoundedCornerShape = RoundedCornerShape(
            topStart = radius,
            topEnd = 0.dp,
            bottomStart = radius,
            bottomEnd = 0.dp
        )
    },
    Middle {
        override fun createOutline(radius: Dp): RoundedCornerShape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    },
    End {
        override fun createOutline(radius: Dp): RoundedCornerShape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = radius,
            bottomStart = 0.dp,
            bottomEnd = radius
        )
    };

    abstract fun createOutline(radius: Dp): RoundedCornerShape
}


@Composable
private fun SelectedButton(
    cornerRadius: Dp,
    index: Int,
    icon: Icon,
    iconSize: Dp,
    position: ButtonPosition,
    onClick: () -> Unit
) {
    val strokeColor =
        if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary

    OutlinedButton(
        modifier = Modifier
            .offset((-1 * index).dp, 0.dp)
            .zIndex(1f),
        onClick = onClick,
        shape = position.createOutline(cornerRadius),
        border = BorderStroke(
            width = 1.dp,
            color = strokeColor
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = strokeColor.copy(
                alpha = 0.2f
            ),
            contentColor = strokeColor
        ),
    ) {
        Image(
            modifier = Modifier.size(iconSize),
            painter = icon.painter,
            contentDescription = icon.description,
            colorFilter = ColorFilter.tint(strokeColor)
        )
    }
}

@Composable
private fun CommonButton(
    cornerRadius: Dp,
    index: Int,
    icon: Icon,
    iconSize: Dp,
    position: ButtonPosition,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .offset((-1 * index).dp, 0.dp)
            .zIndex(0f),
        onClick = onClick,
        shape = position.createOutline(cornerRadius),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary
        ),
    ) {
        Image(
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.size(iconSize),
            painter = icon.painter,
            contentDescription = icon.description
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewToggleRow() {
    AppTheme(darkTheme = true) {
        Surface {
            ToggleIconRow(
                modifier = Modifier,
                selectedIndex = 1,
                onSelectIndex = {},
                icons = listOf(
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flat_black),
                        description = "flat button"
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_natural_note),
                        description = "natural button"
                    ),
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sharp_black),
                        description = "sharp button"
                    )
                )
            )
        }
    }
}