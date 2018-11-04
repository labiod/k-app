package com.bitage.kapp.mapper

import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import java.util.Date

class EntityMapper {
    companion object {
        fun mapChallengeToChallengeEntity(challenge: Challenge): ChallengeEntity {
            return ChallengeEntity(challenge.id,
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.date,
                challenge.series,
                challenge.goal,
                challenge.finished)
        }

        fun mapChallengeEntityToChallenge(challenge: ChallengeEntity): Challenge {
            return Challenge(challenge.id,
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.date,
                challenge.series,
                challenge.goal,
                challenge.finished)
        }

        fun mapChallengeEntitiesListToChallengeList(challenges: List<ChallengeEntity>): List<Challenge> {
            return challenges.map { challenge ->
                mapChallengeEntityToChallenge(challenge)
            }
        }

        fun mapTemplateEntitiesToTemplateList(templates: List<TemplateEntity>): List<Template> {
            return templates.map { template -> mapTemplateEntityToTemplate(template) }
        }

        fun mapTemplateToTemplateEntity(template: Template): TemplateEntity {
            return TemplateEntity(
                template.id,
                template.templateName)
        }

        fun mapTemplateEntityToTemplate(template: TemplateEntity): Template {
            return Template(
                template.id,
                template.templateName)
        }

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