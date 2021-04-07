radio
```
<label for="count">Count:</label>
	<input type="radio" name="count" value="1" th:checked="*{count == 1}">1
	<input type="radio" name="count" value="2" th:checked="*{count == 2}">2
	<input type="radio" name="count" value="3" th:checked="*{count == 3}">3
	<input type="radio" name="count" value="4" th:checked="*{count == 4}">4
	<input type="radio" name="count" value="5" th:checked="*{count == 5}">5<br>
	<div th:if="${#fields.hasErrors('count')}" th:errors="*{count}"></div>
```
intのバリデーション
```
  @Min(0)
	@Max(150)
	private int age;
	
	@Min(1)
	@Max(5)
	private int count;
```
