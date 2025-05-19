package co.mrcomondev.pro.rickandmorty.composables.characters.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.DataSource
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.DefaultRequestOptions
import coil.request.Disposable
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import kotlinx.coroutines.CompletableDeferred

/**
 * Created by gesoft
 */
open class FakeImageLoader() : ImageLoader {

  override val defaults = DefaultRequestOptions()
  override val components = ComponentRegistry()
  override val memoryCache: MemoryCache? get() = null
  override val diskCache: DiskCache? get() = null

  override fun enqueue(request: ImageRequest): Disposable {
    // Always call onStart before onSuccess.
    request.target?.onStart(request.placeholder)
    val result = Color.BLACK.toDrawable()
    request.target?.onSuccess(result)
    return object : Disposable {
      override val job = CompletableDeferred(newResult(request, result))
      override val isDisposed get() = true
      override fun dispose() {}
    }
  }

  override suspend fun execute(request: ImageRequest): ImageResult {
    return newResult(request, Color.BLACK.toDrawable())
  }

  private fun newResult(request: ImageRequest, drawable: Drawable): SuccessResult {
    return SuccessResult(
      drawable = drawable,
      request = request,
      dataSource = DataSource.MEMORY_CACHE
    )
  }

  override fun newBuilder() = throw UnsupportedOperationException()

  override fun shutdown() {}
}