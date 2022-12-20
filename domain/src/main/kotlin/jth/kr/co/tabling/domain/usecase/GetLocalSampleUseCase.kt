package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.SampleRepository
import jth.kr.co.tabling.domain.model.LocalSample

class GetLocalSampleUseCase(private val repository: SampleRepository) {
    suspend operator fun invoke(
    ): List<LocalSample> {
        val result: MutableList<LocalSample> = mutableListOf()
        val response = repository.getLocalSample()

        response.forEach {
            result.add(LocalSample(it.id, it.name))
        }

        return result
    }
}