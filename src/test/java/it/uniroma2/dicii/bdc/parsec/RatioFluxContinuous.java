package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.StatisticsQueryController;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * REQ-FN-11: Ratio between line flux and continuous flux
 */
public class RatioFluxContinuous {

    @Test
    public void calculateFluxRatio() {

        String fluxLine = "OI63";
        String galaxy = "IRAS00182-7112";

        StatisticsQueryController controller = new StatisticsQueryController();
        HashMap result = controller.calculateRatio(fluxLine, galaxy);

        System.out.print(result.keySet());
        System.out.print(result.values());

        Double result3x3 = (Double) result.get("3x3");
        Double result5x5 = (Double) result.get("5x5");
        Double resultC = (Double) result.get("c");

        Double expected3x3 = 6.53;
        Double expected5x5 = 7d;
        Double expectedC = 4.765;

        assertEquals(expected3x3, result3x3, 0.05d);
        assertEquals(expected5x5, result5x5, 0.05d);
        assertEquals(expectedC, resultC, 0.05d);
    }
}
