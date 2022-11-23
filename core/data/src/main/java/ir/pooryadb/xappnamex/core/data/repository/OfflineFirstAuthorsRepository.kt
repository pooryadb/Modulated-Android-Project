/*
package ir.pooryadb.xappnamex.core.data.repository

import ir.pooryadb.xappnamex.core.data.Synchronizer
import ir.pooryadb.xappnamex.core.data.changeListSync
import ir.pooryadb.xappnamex.core.data.model.asEntity
import ir.pooryadb.xappnamex.core.database.dao.AuthorDao
import ir.pooryadb.xappnamex.core.database.model.AuthorEntity
import ir.pooryadb.xappnamex.core.database.model.asExternalModel
import ir.pooryadb.xappnamex.core.datastore.ChangeListVersions
import ir.pooryadb.xappnamex.core.model.data.Author
import ir.pooryadb.xappnamex.core.network.NiaNetworkDataSource
import ir.pooryadb.xappnamex.core.network.model.NetworkAuthor
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

*/
/**
 * Disk storage backed implementation of the [AuthorsRepository].
 * Reads are exclusively from local storage to support offline access.
 *//*

class OfflineFirstAuthorsRepository @Inject constructor(
    private val authorDao: AuthorDao,
    private val network: NiaNetworkDataSource,
) : AuthorsRepository {

    override fun getAuthorStream(id: String): Flow<Author> =
        authorDao.getAuthorEntityStream(id).map {
            it.asExternalModel()
        }

    override fun getAuthorsStream(): Flow<List<Author>> =
        authorDao.getAuthorEntitiesStream()
            .map { it.map(AuthorEntity::asExternalModel) }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.changeListSync(
            versionReader = ChangeListVersions::authorVersion,
            changeListFetcher = { currentVersion ->
                network.getAuthorChangeList(after = currentVersion)
            },
            versionUpdater = { latestVersion ->
                copy(authorVersion = latestVersion)
            },
            modelDeleter = authorDao::deleteAuthors,
            modelUpdater = { changedIds ->
                val networkAuthors = network.getAuthors(ids = changedIds)
                authorDao.upsertAuthors(
                    entities = networkAuthors.map(NetworkAuthor::asEntity)
                )
            }
        )
}
*/
