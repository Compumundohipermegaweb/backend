package com.compumundohipermegaweb.hefesto.api.card.config

import com.compumundohipermegaweb.hefesto.api.card.domain.action.GetActiveCards
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository
import com.compumundohipermegaweb.hefesto.api.card.infra.repository.JpaCardRepository
import com.compumundohipermegaweb.hefesto.api.card.infra.repository.SpringDataCardDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CardConfig {

    @Bean
    fun getActiveCards(cardRepository: CardRepository): GetActiveCards{
        return GetActiveCards(cardRepository)
    }

    @Bean
    fun cardRepository(springDataCardDao: SpringDataCardDao): CardRepository {
        return JpaCardRepository(springDataCardDao)
    }
}