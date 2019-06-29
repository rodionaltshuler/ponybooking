package com.ottamotta.yunar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PonyServiceIntegrationTests {

    @Autowired
    PonyService ponyService;

    @Test
    public void ponyBookedOnceWithConcurrentAccess() {


        String name = "Rainbow Dash";

        List<CompletableFuture<Pony>> bookResults =
                IntStream.range(0, 100)
                        .parallel()
                        .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                            try {
                                return ponyService.bookPony(name);
                            } catch (Exception e) {
                                return null;
                            }
                        }))
                        .collect(Collectors.toList());

        List<Pony> bookedPonies = bookResults
                .stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Assert.assertEquals("1 booking expected, but " + bookedPonies.size() + " occured", 1, bookedPonies.size());

    }


}
