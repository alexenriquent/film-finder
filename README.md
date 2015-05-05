## Film Finder ##
### Comparing movies based on their keywords ###

The Internet Movie Database (www.imdb.com) is the leading site for movie information, with hundreds of thousands of mainstream and not-so-mainstream movie listings. Users may submit keywords related to the plot and these are available on most films. The collected keywords are available for download, and the source file contains some 557,367 titles with keywords, and 5,578,136 keyword entries.

As far as we are concerned, each movie listing is thus a text document, a collection of words drawn from an overall keyword vocabulary or *lexicon*. Our approach is to then represent each movie listing by a binary vector which has a 1 for each of the keywords that is present, and zeros everywhere else. We can then compare listings by counting the number of keywords that they have in common.