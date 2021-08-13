import { extend } from 'vee-validate';

extend('positive', value => {
  return value >= 0;
});
extend('min', value => {
  return value.length >= 3;
});
extend('min', {
  validate(value, args) {
    return value.length >= args.length;
  },
  params: ['length']
});