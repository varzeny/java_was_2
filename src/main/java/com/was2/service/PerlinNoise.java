package com.was2.service;

import java.util.Random;

/**
 * This class provides methods to generate Perlin noise-based terrain, including island shapes
 * with higher elevations at the center and lower at the edges.
 */
public class PerlinNoise {
    /**
     * Generates Perlin noise-based terrain with an island shape.
     * 
     * @param col       the col of the noise array
     * @param row      the row of the noise array
     * @param octaveCount the number of layers used for blending noise
     * @param persistence the impact each layer has while blending
     * @param seed        the seed used for randomization
     * @return a float array containing calculated Perlin noise values shaped like an island
     */
    public float[][] generateIsland(int col, int row, int octaveCount, float persistence, long seed) {
        float[][] baseNoise = generateWhiteNoise(col, row, seed);
        float[][] perlinNoise = generatePerlinNoise(baseNoise, col, row, octaveCount, persistence);
        return createIslandShape(perlinNoise, col, row);
    }

    private float[][] generateWhiteNoise(int col, int row, long seed) {
        Random random = new Random(seed);
        float[][] whiteNoise = new float[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                whiteNoise[i][j] = random.nextFloat();
            }
        }
        return whiteNoise;
    }

    private float[][] generatePerlinNoise(float[][] baseNoise, int col, int row, int octaveCount, float persistence) {
        float[][][] smoothNoise = new float[octaveCount][][];
        float amplitude = 1.0f;
        float totalAmplitude = 0.0f;

        // Generate smooth noise
        for (int i = 0; i < octaveCount; i++) {
            smoothNoise[i] = generateSmoothNoise(baseNoise, col, row, i);
            totalAmplitude += amplitude;
            amplitude *= persistence;
        }

        float[][] perlinNoise = new float[col][row];
        amplitude = 1.0f;

        // Blend noise together
        for (int octave = octaveCount - 1; octave >= 0; octave--) {
            amplitude *= persistence;
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
                }
            }
        }

        // Normalisation
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                perlinNoise[i][j] /= totalAmplitude;
            }
        }

        return perlinNoise;
    }

    private float[][] generateSmoothNoise(float[][] baseNoise, int col, int row, int octave) {
        int samplePeriod = 1 << octave; // calculates 2 ^ k
        float sampleFrequency = 1.0f / samplePeriod;
        float[][] smoothNoise = new float[col][row];

        for (int i = 0; i < col; i++) {
            int sample_i0 = (i / samplePeriod) * samplePeriod;
            int sample_i1 = (sample_i0 + samplePeriod) % col; // wrap around
            float horizontal_blend = (i - sample_i0) * sampleFrequency;

            for (int j = 0; j < row; j++) {
                int sample_j0 = (j / samplePeriod) * samplePeriod;
                int sample_j1 = (sample_j0 + samplePeriod) % row; // wrap around
                float vertical_blend = (j - sample_j0) * sampleFrequency;

                float top = interpolate(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0], horizontal_blend);
                float bottom = interpolate(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1], horizontal_blend);

                smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
            }
        }

        return smoothNoise;
    }

    
    private float[][] createIslandShape(float[][] perlinNoise, int col, int row) {
        float[][] islandMap = new float[col][row];
        float centerX = col / 2f;
        float centerY = row / 2f;
        float maxDistance = Math.min(centerX, centerY);

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                float distance = (float) Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY));
                float distanceFactor = 1 - (distance / maxDistance);

                // Apply a smoother radial gradient
                float rowFactor = (float) Math.pow(distanceFactor, 0.5f); // 부드러운 경사
                islandMap[x][y] = perlinNoise[x][y] * rowFactor;

                // Optionally, add a row cap to reduce peak row
                islandMap[x][y] = Math.min(islandMap[x][y], 0.8f); // 최대 높이 제한
            }
        }

        return islandMap;
    }
    

    private float interpolate(float a, float b, float blend) {
        return a * (1 - blend) + b * blend;
    }
}
