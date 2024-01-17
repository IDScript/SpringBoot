package com.kanggara.budgetin;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
class ServletInitializerTest {

  @Mock
  private SpringApplicationBuilder springApplicationBuilder;

  @Test
  void testIt() {
    ServletInitializer servletInitializer = new ServletInitializer();
    when(springApplicationBuilder.sources(BudgetInApplication.class))
        .thenReturn(springApplicationBuilder);

    SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

    verify(springApplicationBuilder).sources(BudgetInApplication.class);
    assertEquals(springApplicationBuilder, result);
  }

}
