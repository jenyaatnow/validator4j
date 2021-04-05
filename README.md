## validator4j
**Validator4j** is attempt to make the bean validation most natural and close to OOP approach that we use to interact with our data classes. We try to reach this goal by code-generation at compilation phase. We generate so called **V-classes** providing validation API.

### Quickstart
Suppose we have a `User` class. Among others, `User`-class contains an `address` field of type `Address`. We want to validate the data it contains using **validator4j**. For this purpose we need to annotate both `User` and `Address` classes with annotation **`@Validatable`**. Besides there are one strict requirement - source classes have to contain getters for all fields we want to validate (Lombok is allowed). At the compilation phase will be generated two specific classes - `VUser` and `VAddress`. We can interact with these classes in the similar fashion as with the original classes. These classes will contain all fields and getters which present in the original classes, but they will have special types, providing the validation API. Thus `Integer` fields will turn into `VaildatableInteger`, `Set<String>` (or any `Collection` inheritor) will become `ValidatableCollection<String, ValidatableString>`, `Address` - `VAddress`. Also you'll see some constructors. You can find code examples below:

```java
@Validatable
public class User {
    private Integer id;
    private Set<String> roles;
    private Address address;

    // getters
}

@Validatable
public class Address {
    private String street;

    // getters
}
```

<details><summary>Generated V-classes</summary>
<p>
  
```java
public final class VUser extends ValidatableObject<User> {

    private final ValidatableInteger id;

    private final ValidatableCollection<String, ValidatableString> roles;

    private final VAddress address;

    public VUser(final User value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Checks.nonNull(value, "value");
    }

    public VUser(final String path, final User value, final ErrorsContainer errors) {
        super(path, value, errors);

        this.id = new ValidatableInteger(appendPath("id"), safeGet(value, User::getId), errors);
        this.roles = new ValidatableCollection<>(appendPath("roles"), safeGet(value, User::getRoles), errors);
        this.address = new VAddress(appendPath("address"), safeGet(value, User::getAddress), errors);
    }

    public ValidatableInteger getId() {
        return id;
    }

    public ValidatableCollection<String, ValidatableString> getRoles() {
        return roles;
    }

    public VAddress getAddress() {
        return address;
    }
}



public final class VAddress extends ValidatableObject<Address> {

    private final ValidatableString street;

    public VAddress(final Address value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Checks.nonNull(value, "value");
    }

    public VAddress(final String path, final Address value, final ErrorsContainer errors) {
        super(path, value, errors);

        this.street = new ValidatableString(appendPath("street"), safeGet(value, Address::getStreet), errors);
    }

    public ValidatableString getStreet() {
        return street;
    }
}
```

</p>
</details>

So, now we can perform our validation.

```java
public ErrorsContainer validateUser(final User user) {
    final VUser validatableUser = new VUser(user);

    validatableUser.getId().validate((id, reject) -> {
        if (id < 1) reject.accept("Id should be positive number");
    });

    validatableUser.getRoles().validateEach((role, reject) -> {
        if (role.isBlank()) reject.accept("Role should not be blank string");
    });

    validatableUser.getAddress().getStreet().validate((street, reject) -> {
        if (street.isBlank()) reject.accept("Street should not be blank string");
    });

    final ErrorsReport errors = validatableUser.validate();
    return errors;
}
```
As you see `validate...(...)` methods receives lambda of type `ValidationRule` (`BiConsumer` analogue) with two arguments. First is the validating value and second is a function used to reject invalid value. These methods doesn't perform any validation directly, but setup validation rules for further use. Validaton itself happens with `validatableUser.validate()` call - this invocation performs validation both by **JSR 380** constraints and user-defined rules configured earlier.


### For developers
You can build the project as simple as 
```
./gradlew clean build
```

If you want to debug the project with **Intellij Idea** you should create new **Remote JVM Debug** configuration with default settings. Then run project with the following command:
```
./gradlew clean build --stacktrace -x check -Dorg.gradle.jvmargs='-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=5005,suspend=y'
```
Build will be suspended until the remote debugger is attached. Thus all you need is to run newly created **Remote JVM Debug** configuration. 