package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.catalog.CatalogViewModel
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ButtonFilter(
    onFilterClicked: () -> Unit,
) {
    val viewModel = hiltViewModel<CatalogViewModel>()
    ConstraintLayout(constraintSet = buttonFilterConstraint()) {
        Image(
            painter = painterResource(id = R.drawable.filter), contentDescription = "",
            Modifier
                .size(24.dp)
                .clickable(onClick = onFilterClicked)
                .layoutId("button")
        )
        Box(modifier = Modifier.layoutId("filtersCount"),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .size(17.dp)
                    .clip(RoundedCornerShape(size = 50.dp))
                    .background(color = MaterialTheme.colors.mainOrange))
            Text(text = "${viewModel.tagsSelected}", style = Typography.headlineSmall)
        }
    }
}

fun buttonFilterConstraint(): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val filtersCount = createRefFor("filtersCount")

        constrain(button) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(filtersCount) {
            bottom.linkTo(button.top, (-8).dp)
            start.linkTo(button.end, (-8).dp)
        }
    }
}

@Preview
@Composable
fun CustomAppBarPreview() {
    ButtonFilter(
        {  }
    )
}