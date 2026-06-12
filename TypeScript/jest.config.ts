import { pathsToModuleNameMapper } from 'ts-jest';
import { compilerOptions } from './tsconfig.json';

export default {
    testMatch: ['<rootDir>/test/*.spec.ts'],
    collectCoverage: true,
    coverageDirectory: 'coverage',
    coverageProvider: 'v8',
    transform: {
        '^.+\\.tsx?$': 'ts-jest',
    },
    moduleNameMapper: pathsToModuleNameMapper(compilerOptions.paths, { prefix: '<rootDir>/' }),
};
