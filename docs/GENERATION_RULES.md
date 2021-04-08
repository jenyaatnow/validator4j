### More about V-class generation
Corresponding **V-fields** will be generated for all non-static fields of the supported types of the validated class.
Field of unsupported types will be ignored during generation process. You will be noticed about it in console output.
Below is a list of supported types:
- **Enum**
- **Boolean**
- **String**
- **Date**
- **Number** (all inheritors)
- **Collection** (all inheritors)
- classes annotated with **@Validatable**

Inheritance hierarchies is unsupported yet.

Moreover, source class have to contain getters for all validated fields. Otherwise, you will run into
a compile-time error.

In case you need to ignore particular field during generation process you have to mark this field with **`@V4jIgnore`**
annotation *(not implemented)*.
