from PIL import Image, ImageDraw

im = Image.open("test_geometrize.jpg")
print(im.format, im.size, im.mode)
width, height = im.size
pixels = im.load()
