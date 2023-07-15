package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.utils.ExceptionConstants.LottoMachineException.BONUS_NUMBER_MUST_BE_UNIQUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoWinningMachineTest {
    @Test
    @DisplayName("보너스 번호가 당첨 번호에 중복되면 예외가 발생한다")
    void throwExceptionByBonusNumberIsNotUnique() {
        // given
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 1;

        // when - then
        assertThatThrownBy(() -> LottoWinningMachine.drawWinningLottery(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_MUST_BE_UNIQUE.message);
    }

    @Test
    @DisplayName("LottoWinningMachine을 생성한다")
    void success() {
        // given
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        // when
        final LottoWinningMachine lottoWinningMachine = LottoWinningMachine.drawWinningLottery(winningNumbers, bonusNumber);

        // then
        assertAll(
                () -> assertThat(lottoWinningMachine.getWinningLotteryNumbers()).containsExactlyInAnyOrderElementsOf(winningNumbers),
                () -> assertThat(lottoWinningMachine.getBonusNumber()).isEqualTo(bonusNumber)
        );
    }
}