package co.mrcomondev.pro.rickandmorty.presentation.composables.locations

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain

/**
 * Created by gesoft
 */
@Composable
fun LocationCardBody(location: LocationDomain) {
  ConstraintLayout(
    modifier = Modifier
      .fillMaxSize()
  ) {

    val (name, type, residents) = createRefs()

    Text(
      text = location.name,
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.constrainAs(name) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
      })

    Text(
      text = "${location.type} • ${location.dimension}",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
      modifier = Modifier.constrainAs(type) {
        top.linkTo(name.bottom)
      })

    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.constrainAs(residents) {
        top.linkTo(type.bottom)
      }
    ) {
      Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Characters",
        tint = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = "${location.residents.size} residents",
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimaryContainer
      )
    }
  }
}