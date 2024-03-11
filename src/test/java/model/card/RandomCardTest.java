package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomCardTest {

    @DisplayName("랜덤으로 숫자와 모양을 뽑아 카드 1장을 지급한다")
    @Test
    void testPickCard() {
        Card card = RandomCard.pickCard();

        assertThat(card.getNumber()).isNotNull();
        assertThat(card.getShape()).isNotNull();
    }

    @DisplayName("랜덤으로 숫자와 모양을 뽑아 카드 여러장 지급한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10, 29, 60})
    void testPickCards(int count) {
        Cards cards = RandomCard.pickCards(count);
        assertThat(cards.getElements()).hasSize(count);

        cards.getElements().forEach(card -> {
            assertThat(card.getNumber()).isNotNull();
            assertThat(card.getShape()).isNotNull();
        });
    }
}
