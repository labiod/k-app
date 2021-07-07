package com.bitage.kapp.mapper

import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.entity.UserInfoEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import com.bitage.kapp.model.UserInfo
import java.util.Date

/**
 * Class that provider some method to map pure model to room db entity model
 */
class EntityMapperDsl {
    companion object {
        /**
         * Map [Challenge] object to [ChallengeEntity] object
         * @param challenge - challenge to convert
         * @return instance of [ChallengeEntity]
         */
        fun mapToChallengeEntity(challenge: Challenge): ChallengeEntity {
            return mapToChallengeEntity {
                id = challenge.id
                challengeName = challenge.challengeName
                step = challenge.step
                stepProgress = challenge.progress
                date = challenge.date
                series = challenge.series
                goal = challenge.goal
                finished = challenge.finished
            }
        }

        /**
         * Map [ChallengeEntity] object to [Challenge] object
         * @param challenge - challenge entity to convert
         * @return instance of [Challenge]
         */
        fun mapEntityToChallenge(challenge: ChallengeEntity): Challenge {
            return mapToChallenge {
                id = challenge.id
                challengeName = challenge.challengeName
                step = challenge.step
                stepProgress = challenge.progress
                date = challenge.date
                series = challenge.series
                goal = challenge.goal
                finished = challenge.finished
            }
        }

        /**
         * Map list of [ChallengeEntity] to [Challenge]
         * @param challenges - list of challenge to convert
         * @return list of [Challenge]
         */
        fun mapToChallengeList(challenges: List<ChallengeEntity>)
            = challenges.map { challenge -> mapEntityToChallenge(challenge) }

        /**
         * Map list of [TemplateEntity] to [Template]
         * @param templates - list of templates to convert
         * @return list of [Template]
         */
        fun mapToTemplateList(templates: List<TemplateEntity>)
            = templates.map { template -> mapToTemplate(template) }

        /**
         * Map [Template] to [TemplateEntity]
         * @param template - template to convert
         * @return instance of [TemplateEntity]
         */
        fun mapToTemplateEntity(template: Template): TemplateEntity {
            return TemplateEntity(
                template.id,
                template.templateName)
        }

        /**
         * Map [TemplateEntity] to [Template]
         * @param template - template to convert
         * @return instance of [Template]
         */
        fun mapToTemplate(template: TemplateEntity): Template {
            return Template(
                template.id,
                template.templateName)
        }

        /**
         * Map [UserProgressEntity] to [Challenge]
         * @param challengeProgress - user progress for current challenge type
         * @return instance of [Challenge]
         */
        fun mapProgressToChallenge(challengeProgress: UserProgressEntity): Challenge {
            return mapToChallenge {
                challengeName = challengeProgress.challengeType
                step = challengeProgress.step
                stepProgress = challengeProgress.stepProgress
                date = Date()
                series = challengeProgress.series
                goal = challengeProgress.goal
            }
        }

        fun mapListToUserInfoEntities(userInfo: UserInfo): List<UserInfoEntity> {
            return userInfo.map { UserInfoEntity(it.key, it.value) }
        }

        fun mapListOfUserInfoEntityToUserInfo(userInfoEntity: List<UserInfoEntity>): UserInfo {
            return UserInfo(
                userInfoEntity.map { it.fieldName to it.fieldValue }.toMap()
            )
        }

        @ChallengeDsl
        private fun mapToChallenge(cb: ChallengeBuilder.() -> Unit)
            = ChallengeBuilder().apply(cb).challenge()

        @ChallengeDsl
        private fun mapToChallengeEntity(cb: ChallengeBuilder.() -> Unit)
            = ChallengeBuilder().apply(cb).challengeEntity()
    }
}