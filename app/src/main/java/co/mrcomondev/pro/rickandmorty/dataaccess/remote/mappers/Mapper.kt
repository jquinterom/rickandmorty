package co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers

/**
 * Created by gesoft
 */
interface Mapper<D, M> {
  fun mapFromEntity(entity: D): M
  fun mapToEntity(domain: M): D
}