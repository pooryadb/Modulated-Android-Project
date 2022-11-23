/*
package ir.pooryadb.xappnamex.core.domain

import ir.pooryadb.xappnamex.core.data.repository.TopicsRepository
import ir.pooryadb.xappnamex.core.data.repository.UserDataRepository
import ir.pooryadb.xappnamex.core.domain.TopicSortField.NAME
import ir.pooryadb.xappnamex.core.domain.TopicSortField.NONE
import ir.pooryadb.xappnamex.core.domain.model.FollowableTopic
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

*/
/**
 * A use case which obtains a list of topics with their followed state.
 *//*

class GetFollowableTopicsStreamUseCase @Inject constructor(
    private val topicsRepository: TopicsRepository,
    private val userDataRepository: UserDataRepository
) {
    */
/**
 * Returns a list of topics with their associated followed state.
 *
 * @param followedTopicIdsStream - the set of topic ids which are currently being followed. By
 * default the followed topic ids are supplied from the user data repository, but in certain
 * scenarios, such as when creating a temporary set of followed topics, you may wish to override
 * this parameter to supply your own list of topic ids. @see ForYouViewModel for an example of
 * this.
 * @param sortBy - the field used to sort the topics. Default NONE = no sorting.
 *//*

    operator fun invoke(
        followedTopicIdsStream: Flow<Set<String>> =
            userDataRepository.userDataStream.map { userdata ->
                userdata.followedTopics
            },
        sortBy: TopicSortField = NONE
    ): Flow<List<FollowableTopic>> {
        return combine(
            followedTopicIdsStream,
            topicsRepository.getTopicsStream()
        ) { followedIds, topics ->
            val followedTopics = topics
                .map { topic ->
                    FollowableTopic(
                        topic = topic,
                        isFollowed = topic.id in followedIds
                    )
                }
            if (sortBy == NAME) {
                followedTopics.sortedBy { it.topic.name }
            } else {
                followedTopics
            }
        }
    }
}

enum class TopicSortField {
    NONE,
    NAME,
}
*/
