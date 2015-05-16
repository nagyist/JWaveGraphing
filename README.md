# JWaveGraphing
Graphing JWave's wavelets and some transform examples.

At the moment JWaveGraphing is only working for images of size N=2^p, M=2^q where (p,q) € N; p|q = 0, 1, 2, 3, 4, .. . If one dimension of the image is not obeying 2^p or 2^q an exception follows.

However, there are rounding errors due to using ARGB values of the selected image and casting them to double values for the transform.
