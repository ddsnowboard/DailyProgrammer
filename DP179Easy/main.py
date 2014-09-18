from PIL import Image
def greyscale(t):
    i = t[0]+t[1]+t[2]
    return (int(i/3), int(i/3), int(i/3))
file_name = input("Where is your image located? ")
image = Image.open(file_name)
new = image
for i in range(image.size[0]):
    for j in range(image.size[1]):
        new.putpixel((i,j), greyscale(image.getpixel((i, j))))
new.save("new.jpg")