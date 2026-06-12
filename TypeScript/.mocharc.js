module.exports = {
    require: ['ts-node/register', 'tsconfig-paths/register', 'source-map-support/register'],
    spec: 'test/mocha/**/*.spec.ts',
    extension: ['ts'],
};
