package lotto.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final LottoWinningMachine lottoWinningMachine;
    private final UserLottos userLottos;
    private final Map<WinningRank, Integer> winningResult = new EnumMap<>(WinningRank.class);

    private LottoStatistics(
            final LottoWinningMachine lottoWinningMachine,
            final UserLottos userLottos
    ) {
        this.lottoWinningMachine = lottoWinningMachine;
        this.userLottos = userLottos;
        initWinningResult();
        calculateLotteryWinningResult();
    }

    public static LottoStatistics of(
            final LottoWinningMachine lottoWinningMachine,
            final UserLottos userLottos
    ) {
        return new LottoStatistics(lottoWinningMachine, userLottos);
    }

    private void initWinningResult() {
        for (WinningRank winningRank : WinningRank.values()) {
            winningResult.put(winningRank, 0);
        }
    }

    private void calculateLotteryWinningResult() {
        List<Integer> winningLotteryNumbers = lottoWinningMachine.getWinningLotteryNumbers();
        int bonusNumber = lottoWinningMachine.getBonusNumber();

        for (UserLotto userLotto : userLottos.getUserLottos()) {
            List<Integer> lottoNumbers = userLotto.getLottoNumbers();
            int matchCount = getLottoMatchCount(lottoNumbers, winningLotteryNumbers);
            boolean hasBonus = isBonusNumberExists(lottoNumbers, bonusNumber);

            WinningRank winningRank = WinningRank.of(matchCount, hasBonus);
            updateWinningResult(winningRank);
        }
    }

    private int getLottoMatchCount(
            final List<Integer> lottoNumbers,
            final List<Integer> winningLotteryNumbers
    ) {
        return (int) lottoNumbers.stream()
                .filter(winningLotteryNumbers::contains)
                .count();
    }

    private boolean isBonusNumberExists(
            final List<Integer> lottoNumbers,
            final int bonusNumber
    ) {
        return lottoNumbers.contains(bonusNumber);
    }

    private void updateWinningResult(final WinningRank winningRank) {
        winningResult.put(winningRank, winningResult.get(winningRank) + 1);
    }

    public Map<WinningRank, Integer> getWinningResult() {
        return Collections.unmodifiableMap(winningResult);
    }
}