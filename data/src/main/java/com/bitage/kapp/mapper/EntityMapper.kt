package com.bitage.kapp.mapper

import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import java.util.Date

/**
 * Class that provider some method to map pure model to room db entity model
 */
class EntityMapper {
    companion object {
        /**
         * Map [Challenge] object to [ChallengeEntity] object
         * @param challenge - challenge to convert
         * @return instance of [ChallengeEntity]
         */
        fun mapToChallengeEntity(challenge: Challenge): ChallengeEntity {
            return ChallengeEntity(challenge.id,
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.date,
                challenge.series,
                challenge.goal,
                challenge.finished)
        }

        /**
         * Map [ChallengeEntity] object to [Challenge] object
         * @param challenge - challenge entity to convert
         * @return instance of [Challenge]
         */
        fun mapProgressToChallenge(challenge: ChallengeEntity): Challenge {
            return Challenge(challenge.id,
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.date,
                challenge.series,
                challenge.goal,
                challenge.finished)
        }

        /**
         * Map list of [ChallengeEntity] to [Challenge]
         * @param challenges - list of challenge to convert
         * @return list of [Challenge]
         */
        fun mapToChallengeList(challenges: List<ChallengeEntity>): List<Challenge> {
            return challenges.map { challenge ->
                mapProgressToChallenge(challenge)
            }
        }

        /**
         * Map list of [TemplateEntity] to [Template]
         * @param templates - list of templates to convert
         * @return list of [Template]
         */
        fun mapToTemplateList(templates: List<TemplateEntity>): List<Template>
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
            return Challenge(null,
                challengeProgress.challengeType,
                challengeProgress.step,
                challengeProgress.stepProgress,
                Date(),
                challengeProgress.series,
                challengeProgress.goal)
        }
    }
}