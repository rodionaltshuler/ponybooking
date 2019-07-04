package com.ottamotta.yunar

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.stream.IntStream

@RunWith(SpringRunner::class)
@SpringBootTest
class PonyServiceIntegrationTests {

    private val name = "Rainbow Dash"

    @Autowired
    lateinit var ponyService: PonyService

    @Test
    fun `pony booked only once with concurrent access`() {

        val bookResults = IntStream.range(0, 100)
                .parallel()
                .mapToObj { CompletableFuture.supplyAsync<Pony> {
                        try {
                            ponyService.bookPony(name)
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
                .collect(Collectors.toList())

        val bookedPonies = bookResults
                .stream()
                .map { it.join() }
                .filter{ Objects.nonNull(it) }
                .collect(Collectors.toList())

        Assert.assertEquals("1 booking expected, but " + bookedPonies.size + " occured", 1, bookedPonies.size.toLong())

    }

    @After
    @Before
    fun restorePonyState() {
        ponyService.resetPony(name)
    }


}
