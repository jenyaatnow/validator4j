## Guidelines for contributing to Validator4j

Each contribution should be associated with the particular issue.
Choose an [issue](https://github.com/jenyaatnow/validator4j/issues) you want to work on or, if there is no appropriate
issue for the problem you're solving, please create one and describe in details the problem or your improvement proposal.
Some issues tagged with the version number. If your issue has such tag, it means that you should checkout
from the corresponding branch and send your pull request into the same branch. If there is no version tag,
checkout from **develop**.

Since your vision can differ from maintainer's one, it's highly recommended to discuss the implementation details
in the related issue before start coding a new feature. If you don't do this, please don't be surprised
if your pull request is rejected.

### Contribution step-by-step:

* [Fork](https://help.github.com/articles/fork-a-repo/) the **Validator4j**
  [repository](https://github.com/jenyaatnow/validator4j)

* Clone your fork
  
* Create a feature/bugfix branch. Branch name should contain related issue number. Example: **feature/issue-51**

* Write some code. Each changes you want to submit should be properly covered with unit tests.
  Avoid formatting changes to existing code as much as possible

* Commit you changes. Please, pull in the latest upstream changes before commit, using rebase instead of merge.
  All your changes should be contained in the single commit.
  Follow the [best practice](https://chris.beams.io/posts/git-commit/) of writing commit messages.
  Don't forget to add related issue number. Example: **Add validation groups support [Issue: #4]**.

* Push your changes to a topic branch in your fork of the repository

* Initiate a [pull request](https://help.github.com/articles/creating-a-pull-request-from-a-fork/)

* Make sure that all check passed on your pull request

* Finally, update the related issue, adding a comment including a link to the created pull request
