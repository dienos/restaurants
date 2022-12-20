package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.SampleRepository
import jth.kr.co.tabling.domain.model.Sample

class GetSampleUseCase(private val repository: SampleRepository) {
    suspend operator fun invoke(
    ): List<Sample> {
        val result: MutableList<Sample> = mutableListOf()
        val response = repository.getSample()

        response.forEach {
            result.add(Sample(it.name))
        }

        return result
    }
}