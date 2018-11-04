package com.bitage.kapp.repository

import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.Date

class ChallengeRepositoryTest {
    private lateinit var repository: ChallengeRepository

    @Test
    fun testGetChallengeById() {
        val basicChallenge = Challenge(1L, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10)
        repository = mock()

        `when`(repository.getChallengeById(1)).thenReturn(Flowable.just(basicChallenge))

        val result = repository.getChallengeById(1)

        result
            .test()
            .assertSubscribed()
            .assertValue {it.id == 1L}
            .assertComplete()
            .assertNoErrors()


        verify(repository, times(1)).getChallengeById(1)
    }
}